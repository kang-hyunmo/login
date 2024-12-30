function goHome() {
    // 로그인 여부에 따라 변경
    location.href="/"
}
function msgPrint(){
    if (m!=null){
        alert(m)
    }
}


function loginStatus(){
    if(mb){
        // $('#m_id').html(mb.m_id);
        $('#m_name').html(mb.m_name+"님");
        $('.suc').css('display', 'block');  //.show();
        $('.bef').css('display', 'none');  //.hide();
    }else{
        $('.suc').css('display', 'none');  //.hide();
        $('.bef').css('display', 'block');  //.show();


    }
}
