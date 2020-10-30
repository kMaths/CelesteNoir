async function addUser(cat) {
    const user = {
      username: document.getElementById("username").value,
      firstName: document.getElementById("firstname").value,
      lastName: document.getElementById("lastname").value,
      email: document.getElementById("email").value,
      password: document.getElementById("password").value,
      
    };
    fetch("http://localhost:8080/CelesteNoir/register.page", {
      method: "post",
      body: JSON.stringify(user),
      redirect: 'follow'})
      .then(response => {
          document.getElementById("RegisterMessage").innerHTML = "You have registered. Please log in."
        return response.json();
      })
      .catch(function(err) {
          console.info(err + " url: " + url);
          alert("something went wrong")
      });
    
      cat.preventDefault();
  }
  
  document.getElementById("register").addEventListener("click", addUser);