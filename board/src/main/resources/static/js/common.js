//common.js
function goHome() {
    //로그인 여부에 따른 변경
    console.log('goHome')
    location.href = "/"   //localhost:80/
}

function msgPrint(){
   if(m!=null){
        alert(m)
    }
}
function loginStatus(){
    //if(id){
    if(mb){
     //$('#m_id').html(mb.m_id+"님")
     $('#m_name').html(`${mb.m_name}님`)
        $('.suc').css('display', 'block');  //.show();
        $('.bef').css('display', 'none');  //.hide();
    }else{
        $('.suc').css('display', 'none');  //.hide();
        $('.bef').css('display', 'block');  //.show();
    }
}
