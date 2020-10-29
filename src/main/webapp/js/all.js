

function renderTable(reimbs) {
    for (const reimb of reimbs) {
      const thisReimb = reimb;
      const tr = document.createElement("tr");
      const reimbIdTd = document.createElement("td");
      const amountTd = document.createElement("td");
      const typeTD = document.createElement("td");
      const subDateTd = document.createElement("td");
      const statusTd = document.createElement("td");
      const resolvedDateTd =document.createElement("td");
      const descriptionTd = document.createElement("td");
      const receiptTd =document.createElement("td");
      const authorTd = document.createElement("td");
      const aemailTd = document.createElement("td");
      const aidTd = document.createElement("td");
      const resolverTd = document.createElement("td");
      const remailTd = document.createElement("td");
      const ridTd = document.createElement("td");
      const acceptTd = document.createElement("td");
      const denyTd = document.createElement("td");

      reimbIdTd.innerText = reimb.reimbursementId;
      amountTd.innerText = reimb.amount;
      typeTD.innerText = reimb.type.type;
      subDateTd.innerText = reimb.submitted;
      statusTd.innerText = reimb.status.status;
      resolvedDateTd.innerText =reimb.resolved;
      descriptionTd.innerText = reimb.description;
      receiptTd.innerText =reimb.receipt;
      authorTd.innerText = reimb.author.username;
      aemailTd.innerText = reimb.author.email;
      aidTd.innerText = reimb.author.userId;
      resolverTd.innerText = reimb.resolver.username;
      remailTd.innerText = reimb.resolver.email;
      ridTd.innerText = reimb.resolver.userId;
      
      if(reimb.status.status == "pending"){
        const deniedButton = document.createElement("input");
        deniedButton.type = "button";
        deniedButton.className = "btn";
        deniedButton.value = "deny";
        deniedButton.addEventListener("click",() => update("denied", thisReimb));
        denyTd.appendChild(deniedButton);

        const acceptButton = document.createElement("input");
        acceptButton.type = "button";
        acceptButton.className = "btn";
        acceptButton.value = "approve";
        acceptButton.addEventListener("click", ()=> update("approved", thisReimb));
        acceptTd.appendChild(acceptButton);
        
      } else {
        denyTd.innerHTML="--";
        acceptTd.innerHTML="--";
      }

      tr.append(reimbIdTd, amountTd, typeTD, subDateTd, statusTd, resolvedDateTd, descriptionTd, 
        receiptTd, authorTd, aemailTd, aidTd, resolverTd, remailTd, ridTd, denyTd, acceptTd);
      document.getElementById("reimbTableBody").append(tr);
    }
  }

  asyncFetch("http://localhost:8080/CelesteNoir/all.json", renderTable)
  
  async function asyncFetch(url, expression) {
    response = await fetch(url);
    json = await response.json();
    expression(json);
  }
    
  function getAll(){
    const rows = document.getElementById('reimbTableBody').innerHTML='';
    console.log("test");
    asyncFetch("http://localhost:8080/CelesteNoir/all.json", renderTable);
  }
 
  function getByStatus(status){
    const rows = document.getElementById('reimbTableBody').innerHTML='';
    asyncFetch("http://localhost:8080/CelesteNoir/allByStatus.json?statusName="+status, renderTable);
  }

  async function update(message, reimb){
    // message.preventDefault();
    const update = {
      reimb: reimb,
      newStatus: message
    };

    const json = await fetch("http://localhost:8080/CelesteNoir/update.json", {
      method: "post", 
      body : JSON.stringify(update)})
      .catch(function(err) {
        console.info(err + " url: " + url);
        alert("something went wrong")
      });

    getAll(json);

  }

 

 
