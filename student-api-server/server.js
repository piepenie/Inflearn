const express = require("express");

const app = express();
const port = 3000;

app.get("/health", function (request, response) {
  response.json({
    status: "ok",
    message: "서버가 실행 중입니다.",
  });
});

app.get("/hello", function (request, response) {
  response.json({
    status: "ok",
    message: "저는 김파니입니다. 만나서 반가워요",
  });
});

app.listen(port, function () {
  console.log(`API 서버가 <http://localhost>:${port} 에서 실행 중입니다.`);
});