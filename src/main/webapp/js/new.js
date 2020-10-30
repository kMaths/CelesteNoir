async function addReimb(event) {
  const update = {
    amount: document.getElementById("amount").value,
    description: document.getElementById("description").value,
    type : document.getElementById("type").value,
  };
  await fetch("http://localhost:8080/CelesteNoir/new.json", {
    method: "post",
    body: JSON.stringify(update),
  });

}

function renderTable(reimbs) {
    for (const reimb of reimbs) {
      const tr = document.createElement("tr");
      const reimbIdTd = document.createElement("td");
      const amountTd = document.createElement("td");
      const typeTD = document.createElement("td");
      const subDateTd = document.createElement("td");
      const statusTd = document.createElement("td");
      const resolvedDateTd =document.createElement("td");
      const descriptionTd = document.createElement("td");
      const resolverTd = document.createElement("td");
      const remailTd = document.createElement("td");

      reimbIdTd.innerText = reimb.reimbursementId;
      amountTd.innerText = reimb.amount;
      typeTD.innerText = reimb.type.type;
      subDateTd.innerText = reimb.submitted;
      statusTd.innerText = reimb.status.status;
      resolvedDateTd.innerText =reimb.resolved;
      descriptionTd.innerText = reimb.description;
      resolverTd.innerText = reimb.resolver.username;
      remailTd.innerText = reimb.resolver.email;
 

      tr.append(reimbIdTd, amountTd, typeTD, subDateTd, statusTd, resolvedDateTd, descriptionTd, 
        resolverTd, remailTd,);
      document.getElementById("reimbTableBody").append(tr);
    }
  }

  asyncFetch("http://localhost:8080/CelesteNoir/allByUser.json", renderTable)

  async function asyncFetch(url, expression) {
    response = await fetch(url);
    json = await response.json();
    expression(json);
  }
