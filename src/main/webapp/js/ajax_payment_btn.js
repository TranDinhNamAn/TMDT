function addEventPayment(){
    document.querySelectorAll('.btn-vnpay').forEach(btn=>{
        console.log("event")
        btn.addEventListener('click',()=>{
            let total = document.querySelector('.total-pay>span').innerText;
            // let infor = document.querySelector('.information-pay').value;
            const form = document.createElement("form");
            form.method = "POST";
            form.action = "to-vnpay";

            const amountInput = document.createElement("input");
            amountInput.type = "hidden";
            amountInput.name = "amount";
            amountInput.value = total;
            form.appendChild(amountInput);

            const infoInput = document.createElement("input");
            infoInput.type = "hidden";
            infoInput.name = "orderInfo";
            infoInput.value ="Thanh toán đơn hàng của Ocean Seafood qua VnPay";
            form.appendChild(infoInput);

            document.body.appendChild(form);
            form.submit();
        })
    })
    document.querySelectorAll('.btn-momo').forEach(btn=>{
        btn.addEventListener('click',()=>{
            console.log("event")
            let total = document.querySelector('.total-pay>span').innerText;
            const form = document.createElement("form");
            form.method = "POST";
            form.action = "momo";

            const amountInput = document.createElement("input");
            amountInput.type = "hidden";
            amountInput.name = "amount";
            amountInput.value = total;
            form.appendChild(amountInput);

            document.body.appendChild(form);
            form.submit();
        })
    })
}
let pInterface =document.querySelector('.pay-interface');
let pInterfaceID=document.querySelector('.pay-interface>.id-paymentMethods-main').value
if(parseInt(pInterfaceID)===1){
    let button =document.querySelector(".btn-accept>button");
    button.className="";
    button.classList.add('btn-vnpay');
    addEventPayment()
}
if(parseInt(pInterfaceID)===2){
    let button =document.querySelector(".btn-accept>button");
    button.className="";
    button.classList.add('btn-momo');
    addEventPayment()
}
document.querySelectorAll('.pay-method-diaglog').forEach(method=>{
    let idMethod = method.querySelector('.id-paymentMethods-main').value

    if (parseInt(idMethod) === 1){
        method.addEventListener('click',()=>{
            let button =document.querySelector(".btn-accept>button");
            button.className="";
            button.classList.add('btn-vnpay');
            addEventPayment()
        })
    }else if(parseInt(idMethod)===2){
        method.addEventListener('click',()=>{
            let button =document.querySelector(".btn-accept>button");
            button.className="";
            button.classList.add('btn-momo');
            addEventPayment()
        })
    }
})