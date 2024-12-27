//common.js

function goHome(){
    //로그인 여부에 따른
    console.log('goHome')
    location.href="/";  //localhost:80/
}
function msgPrint(){
    if(m!=null){
        alert(m)
    }
}
//loginStatus()
function loginStatus(){
    if(id){
        $('#m_id').html(id+"님");
        $('.suc').css('display','block');
        $('.bef').css('display','none');
    }else{
        $('.suc').css('display','none');
        $('.bef').css('display','block');
    }
}