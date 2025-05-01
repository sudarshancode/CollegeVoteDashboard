document.addEventListener('DOMContentLoaded',function(){

    const addStudentBtn=document.getElementById("add-btn");
    const updateStudentBtn=document.getElementById("update-btn");
    const deleteStudentBtn=document.getElementById("delete-btn");

    const addStudentForm=document.getElementById("addStudentForm");
    const updateStudentForm=document.getElementById("updateStudentForm");
    const deleteStudentForm=document.getElementById("deleteStudentForm");
    

    addStudentBtn.addEventListener('click',function(){
        if(addStudentForm.style.display === 'none' || addStudentForm.style.display === ''){
            addStudentForm.style.display='block';
            addStudentForm.style.border="1px solid rgb(107, 235, 117)";
            updateStudentForm.style.display='none';
            deleteStudentForm.style.display='none';
        }else{
            addStudentForm.style.display='none';
        }
    })
    updateStudentBtn.addEventListener('click',function(){
        if(updateStudentForm.style.display==='none' || updateStudentForm.style.display===''){
            updateStudentForm.style.display='block';
            updateStudentForm.style.border="1px solid rgb(53, 140, 187)";
            addStudentForm.style.display='none';
            deleteStudentForm.style.display='none';
        }else{
            updateStudentForm.style.display='none';
        }
    })
    deleteStudentBtn.addEventListener('click',function(){
        if(deleteStudentForm.style.display==='none' || deleteStudentForm.style.display===''){
            deleteStudentForm.style.display='block';
            deleteStudentForm.style.border="1px solid rgb(177, 61, 61)";
            updateStudentForm.style.display='none';
            addStudentForm.style.display='none';
        }else{
            deleteStudentForm.style.display='none';
        }
    })       
    
})


function showAlertFromURL() {
    const urlParams = new URLSearchParams(window.location.search);

    const errorMessage = urlParams.get("error");
    const successMessage = urlParams.get("message");

    if (errorMessage) {
        alert("❌ Error: " + decodeURIComponent(errorMessage));
    } else if (successMessage) {
        alert("✅ " + decodeURIComponent(successMessage));
    }

    if (errorMessage || successMessage) {
        const newUrl = window.location.origin + window.location.pathname;
        window.history.replaceState(null, "", newUrl);
    }
}

//Run
window.addEventListener("load", showAlertFromURL);



