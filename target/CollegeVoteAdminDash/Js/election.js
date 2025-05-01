document.addEventListener('DOMContentLoaded',function(){

    const addElectionBtn=document.getElementById("add-btn");
    const updateElectionBtn=document.getElementById("update-btn");
    const deleteElectionBtn=document.getElementById("delete-btn");
    const addNoticeBtn=document.getElementById("notice-btn");

    const addElectionForm=document.getElementById("addElectionForm");
    const updateElectionForm=document.getElementById("updateElectionForm");
    const deleteElectionForm=document.getElementById("deleteElectionForm");
    const addNoticeForm=document.getElementById("addNoticeForm");

    addElectionBtn.addEventListener('click',function(){
        if(addElectionForm.style.display === 'none' || addElectionForm.style.display === ''){
            addElectionForm.style.display='block';
            addElectionForm.style.border="1px solid rgb(107, 235, 117)";
            updateElectionForm.style.display='none';
            deleteElectionForm.style.display='none';
            addNoticeForm.style.display='none';
        }else{
            addElectionForm.style.display='none';
        }
    })
    updateElectionBtn.addEventListener('click',function(){
        if(updateElectionForm.style.display==='none' || updateElectionForm.style.display===''){
            updateElectionForm.style.display='block';
            updateElectionForm.style.border="1px solid rgb(53, 140, 187)";
            addElectionForm.style.display='none';
            deleteElectionForm.style.display='none';
            addNoticeForm.style.display='none';
        }else{
            updateElectionForm.style.display='none';
        }
    })
    deleteElectionBtn.addEventListener('click',function(){
        if(deleteElectionForm.style.display==='none' || deleteElectionForm.style.display===''){
            deleteElectionForm.style.display='block';
            deleteElectionForm.style.border="1px solid rgb(177, 61, 61)";
            updateElectionForm.style.display='none';
            addElectionForm.style.display='none';
            addNoticeForm.style.display='none';
        }else{
            deleteElectionForm.style.display='none';
        }
    }) 
    addNoticeBtn.addEventListener('click',function(){
        if(addNoticeForm.style.display==='none' || addNoticeForm.style.display===''){
            addNoticeForm.style.display='block';
            addNoticeForm.style.border="1px solid rgb(32, 82, 181)";
            updateElectionForm.style.display='none';
            addElectionForm.style.display='none';
            deleteElectionForm.style.display='none';
        }else{
            addNoticeForm.style.display='none';
        }
    })      
    
})


// Function to show alert if a message or error exists in the URL
function showAlertFromURL() {
	// Get the current URL parameters
	const urlParams = new URLSearchParams(window.location.search);

	// Get error and success messages
	const errorMessage = urlParams.get("error");
	const successMessage = urlParams.get("message");

	// Show alert if there's an error message
	if (errorMessage) {
		alert(errorMessage);
	} else if (successMessage) {
		alert(successMessage);
	}
	// Remove the parameters from the URL without reloading
	if (errorMessage || successMessage) {
		const newUrl = window.location.origin + window.location.pathname;
		window.history.replaceState(null, "", newUrl);
	}
}

// Run the function when the page loads
window.onload = showAlertFromURL;

