require("dotenv").config();

const express = require("express");
const mysql = require("mysql2/promise");

const app = express();
const port = 3000;

app.use(express.json());

app.use(function (error, request, response, next) {
  if (error instanceof SyntaxError) {
    response.status(400).json({
      message: "JSON 형식이 올바르지 않습니다.",
    });
    return;
  }

  next(error);
});

app.use(function (request, response, next) {
  console.log(`${request.method} ${request.url}`);
  next();
});

const pool = mysql.createPool({
  host: process.env.DB_HOST,
  port: Number(process.env.DB_PORT),
  user: process.env.DB_USER,
  password: process.env.DB_PASSWORD,
  database: process.env.DB_NAME,
  waitForConnections: true,
  connectionLimit: 10,
});

function isValidStudentName(name) {
  if (typeof name !== "string") {
    return false;
  }

  const trimmedName = name.trim();

  return trimmedName !== "" && trimmedName.length <= 50;
}

function isValidStudentScore(score) {
  return Number.isInteger(score) && score >= 0 && score <= 100;
}

function sendServerError(error, response) {
  console.error(error);
  response.status(500).json({
    message: "서버에서 오류가 발생했습니다.",
  });
}

async function findStudentById(id) {
  const [rows] = await pool.query(
    "SELECT id, name, score FROM students WHERE id = ?",
    [id]
  );

  return rows[0];
}

app.get("/health", function (request, response) {
  response.json({
    status: "ok",
    message: "서버가 실행 중입니다.",
  });
});

app.get("/students", async function (request, response) {
  try {
    const [rows] = await pool.query(
      "SELECT id, name, score FROM students ORDER BY id ASC"
    );

    response.json(rows);
  } catch (error) {
    sendServerError(error, response);
  }
});

app.get("/students/names", async function (request, response) {
  try {
    const [rows] = await pool.query(
      "SELECT name FROM students ORDER BY id ASC"
    );

    const names = rows.map((row) => {
      return row.name;
    });

    response.json(names);
  } catch (error) {
    sendServerError(error, response);
  }
});

app.get("/students/passed", async function (request, response) {
  try {
    const [rows] = await pool.query(
      "SELECT id, name, score FROM students WHERE score >= ? ORDER BY id ASC",
      [60]
    );

    response.json(rows);
  } catch (error) {
    sendServerError(error, response);
  }
});

app.get("/students/count", async function (request, response) {
  try {
    const [rows] = await pool.query("SELECT COUNT(*) AS count FROM students");

    response.json({
      count: Number(rows[0].count),
    });
  } catch (error) {
    sendServerError(error, response);
  }
});

app.get("/students/top", async function (request, response) {
  try {
    const [rows] = await pool.query(
      "SELECT id, name, score FROM students ORDER BY score DESC, id ASC LIMIT 1"
    );

    if (rows[0] === undefined) {
      response.status(404).json({
        message: "학생을 찾을 수 없습니다.",
      });
      return;
    }

    response.json(rows[0]);
  } catch (error) {
    sendServerError(error, response);
  }
});

app.get("/students/search", async function (request, response) {
  try {
    const minScoreQuery = request.query.minScore;

    if (typeof minScoreQuery !== "string" || minScoreQuery.trim() === "") {
      response.status(400).json({
        message: "minScore는 숫자여야 합니다.",
      });
      return;
    }

    const minScore = Number(minScoreQuery);

    if (!Number.isFinite(minScore)) {
      response.status(400).json({
        message: "minScore는 숫자여야 합니다.",
      });
      return;
    }

    const [rows] = await pool.query(
      "SELECT id, name, score FROM students WHERE score >= ? ORDER BY score DESC, id ASC",
      [minScore]
    );

    response.json(rows);
  } catch (error) {
    sendServerError(error, response);
  }
});

app.get("/students/:id", async function (request, response) {
  try {
    const id = Number(request.params.id);

    if (!Number.isInteger(id)) {
      response.status(400).json({
        message: "id는 숫자여야 합니다.",
      });
      return;
    }

    const student = await findStudentById(id);

    if (student === undefined) {
      response.status(404).json({
        message: "학생을 찾을 수 없습니다.",
      });
      return;
    }

    response.json(student);
  } catch (error) {
    sendServerError(error, response);
  }
});

app.post("/students", async function (request, response) {
  try {
    const body = request.body || {};
    const name = body.name;
    const score = body.score;

    if (!isValidStudentName(name) || !isValidStudentScore(score)) {
      response.status(400).json({
        message: "name은 1자 이상 50자 이하이고, score는 0부터 100 사이의 정수여야 합니다.",
      });
      return;
    }

    const [result] = await pool.query(
      "INSERT INTO students (name, score) VALUES (?, ?)",
      [name.trim(), score]
    );

    const newStudent = await findStudentById(result.insertId);

    response.status(201).json(newStudent);
  } catch (error) {
    sendServerError(error, response);
  }
});

app.put("/students/:id", async function (request, response) {
  try {
    const id = Number(request.params.id);

    if (!Number.isInteger(id)) {
      response.status(400).json({
        message: "id는 숫자여야 합니다.",
      });
      return;
    }

    const body = request.body || {};
    const name = body.name;
    const score = body.score;

    if (!isValidStudentName(name) || !isValidStudentScore(score)) {
      response.status(400).json({
        message: "name은 1자 이상 50자 이하이고, score는 0부터 100 사이의 정수여야 합니다.",
      });
      return;
    }

    const [result] = await pool.query(
      "UPDATE students SET name = ?, score = ? WHERE id = ?",
      [name.trim(), score, id]
    );

    if (result.affectedRows === 0) {
      response.status(404).json({
        message: "학생을 찾을 수 없습니다.",
      });
      return;
    }

    const updatedStudent = await findStudentById(id);

    response.json(updatedStudent);
  } catch (error) {
    sendServerError(error, response);
  }
});

app.patch("/students/:id", async function (request, response) {
  try {
    const id = Number(request.params.id);

    if (!Number.isInteger(id)) {
      response.status(400).json({
        message: "id는 숫자여야 합니다.",
      });
      return;
    }

    const currentStudent = await findStudentById(id);

    if (currentStudent === undefined) {
      response.status(404).json({
        message: "학생을 찾을 수 없습니다.",
      });
      return;
    }

    const body = request.body || {};
    const name = body.name;
    const score = body.score;
    const hasName = name !== undefined;
    const hasScore = score !== undefined;

    if (!hasName && !hasScore) {
      response.status(400).json({
        message: "수정할 name이나 score를 입력해야 합니다.",
      });
      return;
    }

    if (hasName && !isValidStudentName(name)) {
      response.status(400).json({
        message: "name은 1자 이상 50자 이하의 문자열이어야 합니다.",
      });
      return;
    }

    if (hasScore && !isValidStudentScore(score)) {
      response.status(400).json({
        message: "score는 0부터 100 사이의 정수여야 합니다.",
      });
      return;
    }

    const updatedName = hasName ? name.trim() : currentStudent.name;
    const updatedScore = hasScore ? score : currentStudent.score;

    await pool.query(
      "UPDATE students SET name = ?, score = ? WHERE id = ?",
      [updatedName, updatedScore, id]
    );

    const updatedStudent = await findStudentById(id);

    response.json(updatedStudent);
  } catch (error) {
    sendServerError(error, response);
  }
});

app.delete("/students/:id", async function (request, response) {
  try {
    const id = Number(request.params.id);

    if (!Number.isInteger(id)) {
      response.status(400).json({
        message: "id는 숫자여야 합니다.",
      });
      return;
    }

    const student = await findStudentById(id);

    if (student === undefined) {
      response.status(404).json({
        message: "학생을 찾을 수 없습니다.",
      });
      return;
    }

    await pool.query("DELETE FROM students WHERE id = ?", [id]);

    response.json({
      message: "학생을 삭제했습니다.",
      student: student,
    });
  } catch (error) {
    sendServerError(error, response);
  }
});

app.use(function (request, response) {
  response.status(404).json({
    message: "요청한 API를 찾을 수 없습니다.",
  });
});

app.listen(port, function () {
  console.log(`API 서버가 <http://localhost>:${port} 에서 실행 중입니다.`);
});

// const express = require("express");

// const app = express();
// const port = 3000;

// app.use(express.json());

// app.use(function (request, response, next) {
//   console.log(`${request.method} ${request.url}`);
//   next();
// });

// const students = [
//   { id: 1, name: "김하나", score: 85 },
//   { id: 2, name: "이준호", score: 58 },
//   { id: 3, name: "박서연", score: 92 },
//   { id: 4, name: "최민수", score: 76 },
// ];

// app.get("/health", function (request, response) {
//   response.json({
//     status: "ok",
//     message: "서버가 실행 중입니다.",
//   });
// });

// app.get("/students", function (request, response) {
//   response.json(students);
// });

// app.get("/students/names", function (request, response) {
//   const studentNames = students.map((student) => {
//     return student.name;
//   });

//   response.json(studentNames);
// });

// app.get("/students/passed", function (request, response) {
//   const passedStudents = students.filter((student) => {
//     return student.score >= 60;
//   });

//   response.json(passedStudents);
// });

// app.get("/students/help", function (request, response){
//    response.json({
//     message: "학생 API 연습 서버입니다.",
//     routes: [
//       "GET /students",
//       "GET /students/search?minScore=70",
//       "GET /students/1",
//       "POST /students"
//     ]
//   });
// });

// app.get("/students/example", function (request, response){
//   response.json({
//     "id": 1,
//     "name": "예시학생",
//     "score": 90
//   });
// });

// // app.get("/students/preview", function (request, response){
// //   response.json({
    

// //   });
// // });

// app.get("/students/top", function (request, response) {
//   let topStudent = students[0];

//   for (let index = 1; index < students.length; index++) {
//     if (students[index].score > topStudent.score) {
//       topStudent = students[index];
//     }
//   }

//   response.json(topStudent);
// });

// app.get("/students/search", function (request, response) {
//   const minScore = Number(request.query.minScore);

//   if (!Number.isFinite(minScore)) {
//     response.status(400).json({
//       message: "minScore는 숫자여야 합니다.",
//     });
//     return;
//   }

//   const filteredStudents = students.filter((student) => {
//     return student.score >= minScore;
//   });

//   response.json(filteredStudents);
// });

// app.get("/students/:id", function (request, response) {
//   const id = Number(request.params.id);

//   if (!Number.isInteger(id)) {
//     response.status(400).json({
//       message: "id는 숫자여야 합니다.",
//     });
//     return;
//   }

//   let student = undefined;

//   for (let index = 0; index < students.length; index++) {
//     if (students[index].id === id) {
//       student = students[index];
//     }
//   }

//   if (student === undefined) {
//     response.status(404).json({
//       message: "학생을 찾을 수 없습니다.",
//     });
//     return;
//   }

//   response.json(student);
// });

// app.post("/students", function (request, response) {
//   const body = request.body || {};
//   const name = body.name;
//   const score = body.score;

//   if (
//     typeof name !== "string" ||
//     name.trim() === "" ||
//     typeof score !== "number" ||
//     !Number.isFinite(score)
//   ) {
//     response.status(400).json({
//       message: "name과 숫자 score를 입력해야 합니다.",
//     });
//     return;
//   }

//   const newStudent = {
//     id: students.length + 1,
//     name: name.trim(),
//     score: score,
//   };

//   students.push(newStudent);

//   response.status(201).json(newStudent);
// });

// app.put("/students/:id", function (request, response) {
//   const id = Number(request.params.id);

//   if (!Number.isInteger(id)) {
//     response.status(400).json({
//       message: "id는 숫자여야 합니다.",
//     });
//     return;
//   }

//   let studentIndex = -1;

//   for (let index = 0; index < students.length; index++) {
//     if (students[index].id === id) {
//       studentIndex = index;
//     }
//   }

//   if (studentIndex === -1) {
//     response.status(404).json({
//       message: "학생을 찾을 수 없습니다.",
//     });
//     return;
//   }

//   const body = request.body || {};
//   const name = body.name;
//   const score = body.score;

//   if (
//     typeof name !== "string" ||
//     name.trim() === "" ||
//     typeof score !== "number" ||
//     !Number.isFinite(score)
//   ) {
//     response.status(400).json({
//       message: "name과 숫자 score를 입력해야 합니다.",
//     });
//     return;
//   }

//   const updatedStudent = {
//     id: id,
//     name: name.trim(),
//     score: score,
//   };

//   students[studentIndex] = updatedStudent;

//   response.json(updatedStudent);
// });

// app.patch("/students/:id", function (request, response) {
//   const id = Number(request.params.id);

//   if (!Number.isInteger(id)) {
//     response.status(400).json({
//       message: "id는 숫자여야 합니다.",
//     });
//     return;
//   }

//   let student = undefined;

//   for (let index = 0; index < students.length; index++) {
//     if (students[index].id === id) {
//       student = students[index];
//     }
//   }

//   if (student === undefined) {
//     response.status(404).json({
//       message: "학생을 찾을 수 없습니다.",
//     });
//     return;
//   }

//   const body = request.body || {};
//   const name = body.name;
//   const score = body.score;
//   const hasName = name !== undefined;
//   const hasScore = score !== undefined;

//   if (!hasName && !hasScore) {
//     response.status(400).json({
//       message: "수정할 name 또는 score를 입력해야 합니다.",
//     });
//     return;
//   }

//   if (hasName && (typeof name !== "string" || name.trim() === "")) {
//     response.status(400).json({
//       message: "name은 비어 있지 않은 문자열이어야 합니다.",
//     });
//     return;
//   }

//   if (hasScore && (typeof score !== "number" || !Number.isFinite(score))) {
//     response.status(400).json({
//       message: "score는 숫자여야 합니다.",
//     });
//     return;
//   }

//   if (hasName) {
//     student.name = name.trim();
//   }

//   if (hasScore) {
//     student.score = score;
//   }

//   response.json(student);
// });

// app.delete("/students/:id", function (request, response) {
//   const id = Number(request.params.id);

//   if (!Number.isInteger(id)) {
//     response.status(400).json({
//       message: "id는 숫자여야 합니다.",
//     });
//     return;
//   }

//   let studentIndex = -1;

//   for (let index = 0; index < students.length; index++) {
//     if (students[index].id === id) {
//       studentIndex = index;
//     }
//   }

//   if (studentIndex === -1) {
//     response.status(404).json({
//       message: "학생을 찾을 수 없습니다.",
//     });
//     return;
//   }

//   const deletedStudents = students.splice(studentIndex, 1);

//   response.json({
//     message: "학생을 삭제했습니다.",
//     student: deletedStudents[0],
//   });
// });

// app.use(function (request, response) {
//   response.status(404).json({
//     message: "요청한 API를 찾을 수 없습니다.",
//   });
// });

// app.listen(port, function () {
//   console.log(`API 서버가 <http://localhost>:${port} 에서 실행 중입니다.`);
// });