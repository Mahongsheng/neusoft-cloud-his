var $inputs;
var inputs = [];
$(function () {
    'use strict';

    $inputs = $('[data-rule]');

    $inputs.each(function (index, node) {
        let temp = new Input(node);
        inputs.push(temp);
    })
});

//提交前的表单验证
function getInputsByFunc() {
    'use strict';

    inputs = [];
    $inputs = $('[data-rule]');

    $inputs.each(function (index, node) {
        let temp = new Input(node);
        inputs.push(temp);
    })
}

function submit_validate() {
    $inputs.trigger('blur');
    for (let i = 0; i < inputs.length; i++) {
        let item = inputs[i];
        let result = item.validator.is_valid();
        if (!result) {
            return result;
        }
    }
    return true;
}

