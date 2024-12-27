//common.js

function goHome(){
    //로그인 여부에 따른
    console.log('goHome')
    location.href="/";  //localhost:80/
}
function msgPrint(){
    if(m!=''){
        alert(m)
    }
}
//loginStatus()