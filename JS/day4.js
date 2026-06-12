/////////// 1

// function makeStars(n){
//   let star ="";

//   for(i=0; i<n;i++){
//     star += "*";
//   }
//   console.log(star)
// }
// makeStars(5)


/////////// 2

// function makeNumberLine(n){
//   let number = "";

//   for(i=1; i<=n; i++){
//     number += i + " ";
//   }
//   console.log(number)
// }
// makeNumberLine(5)

/////////// 3

// function makeVerticaStars(n){
//   let star = "";
//   for(i = 1; i<=n; i++){
//     for(j=1; j<=i; j++){
//       star = "*";
//     }
//     console.log(star)
//   }
// }
// makeVerticaStars(3)

/////////// 4

// function makeSquare(n){
//   for(i=1; i<=n; i++){
//     let star = "";
//     for(j=1; j<=n; j++){
//       star += "*"
//     }
//     console.log(star)
//   }
// }
// makeSquare(3)

/////////// 5

// function makeRectangle(width, heigth){
//     for(i=1; i<=heigth; i++){
//       let star = "";
//       for(j=1; j<=width; j++){
//         star += "*"
//     }
//     console.log(star)
//   }
// }
// makeRectangle(3,4)

/////////// 6

// function makeTriagle(n){
//   for(i=1; i<=n; i++){
//     let star = "";
//     for(j=1; j<=i; j++){
//       star +="*";
//     }
//     console.log(star)
//   }
// }
// makeTriagle(4)

/////////// 7

// function makeReverseTriangle(n){
//   for(i=n; i>=1; i--){
//     let star ="";
//     for(j=1; j<=i; j++){
//       star +="*";
//     }
//     console.log(star)
//   }
// }
// makeReverseTriangle(4)

/////////// 8

// function makeRightTriangle(n){
//   for(i=1; i<=n; i++) {
//     let star = "";
//     let space = "";
//     for(j=1; j<=i; j++){
//       star += "*";
//     }
//     for(j=1; j<=n-i; j++){
//       space += " ";
//     }
//     console.log(space+star)
//   }
// } 
// makeRightTriangle(4)

/////////// 9

// function makeNumberTriangle(n){
//   for(i=1; i<=n; i++){
//     let number = "";
//     for(j=1; j<=i; j++){
//       number += j;
//     }
//     console.log(number)
//   }
// }
// makeNumberTriangle(4)

/////////// 10 << 어려워 ㅠㅠㅠ 

function makeBorderSquare(n){
  for(i=1; i<=n; i++){
    let star = "";
    let space = "";
    for(j=1; j<=n; j++){
      star += "*";
    }
    console.log(star)
  }
}

makeBorderSquare(4)

/////////// 11

// function makeReverseNumberLine(n){
//   let number = "";

//   for(i=n; i>=1; i--){
//     number += i + " ";
//   }
//   console.log(number)
// }
// makeReverseNumberLine(5)

/////////// 12

