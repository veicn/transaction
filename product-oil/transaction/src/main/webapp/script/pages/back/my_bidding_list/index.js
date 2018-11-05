$(function () {
    commonCPY.select();
    /*commonCPY.picker();
    var  startTime=commonCPY.picker("startTime");
    var  endTime=commonCPY.picker("endTime");
    startTime()
    endTime()*/
    commonCPY.datePicker(['startTime','endTime'])

});

$("#submitForm").on("click", function () {
    document.getElementById("myBiddingList").submit();
});