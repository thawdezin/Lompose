// Wait for the document to be loaded
document.addEventListener('DOMContentLoaded', function() {
  // Get the JSON data passed from Jetpack Compose
  var jsonData = "{{JSON_DATA}}";
  
  // Log the JSON data to console
  console.log("JSON Data:", jsonData);
  
  // Update the <h1> tag with the JSON data
  var h1Tag = document.getElementsByTagName('h1')[0];
  h1Tag.textContent = "Chat Example: " + jsonData;
});
