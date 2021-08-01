const mealsAjaxUrl = "restui/meals/";

// https://stackoverflow.com/a/5064235/548473
const ctx = {
    ajaxUrl: mealsAjaxUrl
};

// $(document).ready(function () {
$(function () {
    makeEditable(
        $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "dateTime"
                },
                {
                    "data": "description"
                },
                {
                    "data": "calories"
                },
                {
                    "defaultContent": "Edit",
                    "orderable": false
                },
                {
                    "defaultContent": "Delete",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "asc"
                ]
            ]
        })
    );
});

$.urlParam = function(name){
    let results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
    if (results==null) {
        return null;
    }
    return decodeURI(results[1]) || 0;
}

function filter() {
    $.ajax({
        url: ctx.ajaxUrl + "/filter",
        type: "get", //send it through get method
        data: {
            startDate: $.urlParam("startDate"),
            startTime: $.urlParam("startTime"),
            endDate: $.urlParam("endDate"),
            endTime: $.urlParam("endTime")
        }
    }).done(function () {
        updateTable();
    });
}