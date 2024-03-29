$(document).ready(initPage());

function initPage() {
    loadCategories();
    loadSales();
}


function loadSales() {
    var i = 0;
    $.ajax({
        url: "../restservices/sale",
        method: "GET",
        success: [function(data) {
            $.each(data, function(i,sale) {
                i++;
                $(".sales").append(
                    "<div class=container-fluid>" +
                        "<div class=row>" +
                            "<div class=col-md-3>" +
                                "<div class=test></div>" +
                            "</div>" +
                            "<div class=col-md-6>" +
                                "<div class=list>" +
                                    "<div class=col-md-4>" +
                                        "<div class=picture></div>" +
                                    "</div>" +
                                    "<div class=col-md-8>" +
                                        "<div class=info onclick=divClick("+ sale.product.id +")>" +
                                            "<label href=\"..\\HTML\\Product.html\">" + sale.product.id + "</label><br>" +
                                            "<label> Name:</label>" +
                                            "<label>" + sale.product.name + "</label><br>" +
                                            "<label> Price:</label>" +
                                            "<label>" + sale.product.price + "</label><br>" +
                                            "<label> SalePrice:</label>" +
                                            "<label>" + sale.salePrice + "</label><br>" +
                                            "<label> From:</label>" +
                                            "<label>" + sale.fromDate + "</label><br>" +
                                            "<label> To: </label>" +
                                            "<label>" + sale.toDate + "</label><br>" +
                                            "<label> Description: </label>" +
                                            "<label>" + sale.product.description + "</label>" +

                                        "</div>" +
                                    "</div>" +
                                "</div>" +
                            "</div>" +
                            "<div class=col-md-3>" +
                                "<div class=test></div>" +
                            "</div>" +
                        "</div>" +
                    "</div>"
                )
            });
        }]
    });
};

function divClick(id) {
    console.log(id);
    sessionStorage.setItem('id', id);
    location.href='../HTML/Product.html';
};