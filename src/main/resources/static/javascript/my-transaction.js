$(document).ready(function () {
    var category = $("input[name='category']");

    category.on("click", function () {

        var choice = $('input:radio[name="category"]:checked').val();

        var containers = $(".my-transaction-card-container");

        var i;

        for (i = 0; i< containers.length;i++){
            $(containers[i]).css("display", "none");
        }

        var targets = null;

        switch (choice) {
            case "1":{
                targets = $(".assessing");
                break;
            }
            case "2":{
                targets = $(".contracting");
                break;
            }
            case "3":{
                targets = $(".transporting");
                break;
            }
            case "4":{
                targets = $(".pledging");
                break;
            }
            case "5":{
                targets = $(".finishing");
                break;
            }
        }
        if (targets !== null){
            for (i = 0;i < targets.length;i++){
                $(targets[i]).css("display", "block")
            }
        }

    })

});