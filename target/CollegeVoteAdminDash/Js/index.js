function showAlertFromURL() {
	// Get the current URL parameters
	const urlParams = new URLSearchParams(window.location.search);

	const successMessage = urlParams.get("message");

	// Show alert if there's an error message
	if (successMessage) {
		alert(successMessage);
	}
	// Remove the parameters from the URL without reloading
	if (successMessage) {
		const newUrl = window.location.origin + window.location.pathname;
		window.history.replaceState(null, "", newUrl);
	}
}

// Run the function when the page loads
window.onload = showAlertFromURL;