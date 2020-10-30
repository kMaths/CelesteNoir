

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

      tr.append(reimbIdTd, amountTd, typeTD, subDateTd, statusTd, resolvedDateTd, descriptionTd, 
        receiptTd, authorTd, aemailTd, aidTd, resolverTd, remailTd, ridTd);
      document.getElementById("reimbTableBody").append(tr);
    }
  }

  asyncFetch("http://localhost:8080/CelesteNoir/allByUser.json", renderTable)
  
  async function asyncFetch(url, expression) {
    response = await fetch(url);
    json = await response.json();
    expression(json);
  }
    