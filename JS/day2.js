// const course = "JavaScript";

// if (true) {
//   const course = "Node.js";
//   const message = "블록 안에서 만든 값입니다.";

//   console.log(course);
//   console.log(message);
// }

// console.log(course);

// console.log(createMessageDeclaration("김하나"));

// function createMessageDeclaration(name) {
//   return `${name}님 환영합니다.`;
// }

// const emptyName = "";
// const missingName = undefined;
// const selectedName = null;

// console.log(`empty: '${emptyName ?? "이름없음"}'`);
// console.log(`missing: '${missingName ?? "이름없음"}'`);
// console.log(`selected: '${selectedName ?? "이름없음"}'`);

// function getRoleLabel(isAdmin) {
//   return isAdmin ? "관리자" : "일반 사용자";
// }

// console.log(getRoleLabel(true));
// console.log(getRoleLabel(false));
// function toNumberOrDefault(value, defaultValue) {
//   const number = Number(value);

//   if (!Number.isFinite(number)) {
//     return defaultValue;
//   }

//   return number;
// }

// console.log(toNumberOrDefault("10", 0));
// console.log(toNumberOrDefault("abc", 0));

// let command;

// function printCommandName(commandName) {
//   console.log(commandName);
// }

// function doNothing() {
// }

// const selectedStudent = null;

// console.log(command);
// printCommandName();
// console.log(doNothing());
// console.log(selectedStudent);
// console.log(command === undefined);
// console.log(selectedStudent === null);

const retryCount = 0;
const count = retryCount || 3;

console.log(count);