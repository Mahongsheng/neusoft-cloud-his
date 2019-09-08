// 等待文档加载完毕后执行
$(function () {
    'use strict';

    let $inputs = $('[data-rule]');
    let inputs = [];

    $inputs.each(function (index, node) {
        let temp = new Input(node);
        inputs.push(temp);
    })

    function submit_validate() {
        $inputs.trigger('blur');
        for (let i = 0; i < inputs.length; i++) {
            let item = inputs[i];
            let result = item.validator.is_valid();
            if (!result){
                alert("不合法");
                return;
            }
        }
    }
});