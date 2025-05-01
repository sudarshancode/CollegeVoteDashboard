document.addEventListener('DOMContentLoaded', function() {

	const addCandidateBtn = document.getElementById("add-btn");
	const updateCandidateBtn = document.getElementById("update-btn");
	const deleteCandidateBtn = document.getElementById("delete-btn");

	const addCandidateForm = document.getElementById("addCandidateForm");
	const updateCandidateForm = document.getElementById("updateCandidateForm");
	const deleteCandidateForm = document.getElementById("deleteCandidateForm");



	addCandidateBtn.addEventListener('click', function() {
		if (addCandidateForm.style.display === 'none' || addCandidateForm.style.display === '') {
			addCandidateForm.style.display = 'block';
			addCandidateForm.style.border = "1px solid rgb(107, 235, 117)";
			updateCandidateForm.style.display = 'none';
			deleteCandidateForm.style.display = 'none';
		} else {
			addCandidateForm.style.display = 'none';
		}
	})
	updateCandidateBtn.addEventListener('click', function() {
		if (updateCandidateForm.style.display === 'none' || updateCandidateForm.style.display === '') {
			updateCandidateForm.style.display = 'block';
			updateCandidateForm.style.border = "1px solid rgb(53, 140, 187)";
			addCandidateForm.style.display = 'none';
			deleteCandidateForm.style.display = 'none';
		} else {
			updateCandidateForm.style.display = 'none';
		}
	})
	deleteCandidateBtn.addEventListener('click', function() {
		if (deleteCandidateForm.style.display === 'none' || deleteCandidateForm.style.display === '') {
			deleteCandidateForm.style.display = 'block';
			deleteCandidateForm.style.border = "1px solid rgb(177, 61, 61)";
			updateCandidateForm.style.display = 'none';
			addCandidateForm.style.display = 'none';
		} else {
			deleteCandidateForm.style.display = 'none';
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

