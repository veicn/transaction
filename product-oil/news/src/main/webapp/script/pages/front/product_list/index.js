function sendQueryData(inputId, inputValue) {
    $('#' + inputId).val(inputValue);
    $('#saleSheetQueryForm').submit();
}