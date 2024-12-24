let abcElems= document.getElementsByClassName('abc');
console.log(abcElems);
let oneElem = document.getElementById('one');
console.log(oneElem);

let elems = document.querySelectorAll('.fruit');
console.log(elems);
// console.log(elms[0].dataset.fruitName);
// console.log(elms[1].dataset.fruitName);
for (let i=0;i<elems.length;i++){
    console.log(elems[i].dataset.fruitName);
}
//for each 문
for(let elem of elems){
    console.log(elem.dataset.fruitName);
}
elems.forEach(e=>{
    console.log(e.dataset.fruitName);   
})