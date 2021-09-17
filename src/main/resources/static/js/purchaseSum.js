let tbodyRowCount = document.getElementById("purchaseTable").tBodies[0];
let amountOfPurchases = 0;
let sumVal = 0
for (let i = 0; i < tbodyRowCount.rows.length; i++) {
    let value = tbodyRowCount.rows[i].cells[3].children.item(0).innerHTML.split("$")
    amountOfPurchases += parseInt(document.getElementById("purchaseTable").tBodies[0].rows[i].cells[2].children.item(1).innerHTML)
    sumVal = sumVal + parseFloat(value[0]);
}
document.getElementById("phoneSum").innerHTML = "Sum of your purchases: " + sumVal + "$"

document.getElementById("amountOfPurchasesAndSum").innerHTML = "Amount of purchases: " + amountOfPurchases +
    "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
    + " Sum of purchases: &nbsp;" + sumVal + "$";