function onblur_proposalDirectCostAmount( directAmountField ) {
    updateProposalTotalAmount( directAmountField.name, findElPrefix( directAmountField.name ) + ".proposalIndirectCostAmount" );
}

function onblur_proposalIndirectCostAmount( indirectAmountField ) {
    updateProposalTotalAmount( findElPrefix( indirectAmountField.name ) + ".proposalDirectCostAmount", indirectAmountField.name );
}

function updateProposalTotalAmount( directAmountFieldName, indirectAmountFieldName ) {
    var directAmount = getElementValue( directAmountFieldName );
    var indirectAmount = getElementValue( indirectAmountFieldName );
    var totalFieldName = findElPrefix( directAmountFieldName ) + ".proposalTotalAmount";
    if ( isCurrencyNumber( directAmount ) && isCurrencyNumber( indirectAmount ) ) {
        var totalValue = formatCurrency( parseCurrency( directAmount ) + parseCurrency( indirectAmount ) );
        setRecipientValue( totalFieldName, totalValue );
    }
    else {
        setRecipientValue( totalFieldName, "" );
    }
}

function isCurrencyNumber( value ) {
    return /^[($-]*\d{1,3}(,?\d{3})*(\.\d{0,2})?\)?$/.test( value.toString().trim() );
}

function parseCurrency( value ) {
    value = value.toString().trim();
    var negative = /^\(.*\)$/.test(value);
    return (negative ? -1 : 1) * parseFloat( value.replace(/[($,]/g, "") );
}

function formatCurrency( amount ) {
    var negative = amount < 0;
    var roundedParts = (Math.abs(amount) + 0.005).toString().split(".");
    var whole = roundedParts[0];
    var fraction = roundedParts.length < 2 ? "00" : (roundedParts[1] + "00").substring(0, 2);
    var groups = [];
    while (whole.length > 3) {
        groups.unshift( whole.substring(whole.length - 3) );
        whole = whole.substring(0, whole.length - 3);
    }
    if (whole.length > 0) {
        groups.unshift(whole);
    }
    // Kuali's CurrencyFormatter is not displaying the $ symbol, so this function doesn't either.
    return (negative ? "(" : "") + groups.join(",") + "." + fraction + (negative ? ")" : "");
}
