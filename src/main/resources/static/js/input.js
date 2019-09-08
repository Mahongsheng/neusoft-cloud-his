$(function () {
    'use strict';

    window.Input = function (selector) {
        let $ele;
        let $error_ele;
        let rule = {};
        let me = this;

        function init() {
            find_ele();
            get_error_ele();
            parse_rule();
            me.load_validator();
            listen();
        }

        this.load_validator = function () {
            let val = me.get_ele();
            this.validator = new Validator(val, rule);

        };

        this.get_ele = function () {
            return $ele.val();
        };

        function find_ele() {
            if (selector instanceof jQuery) {
                $ele = selector;
            } else {
                $ele = $(selector);
            }
        }

        function parse_rule() {
            let rule_str = $ele.data("rule");
            if (!rule_str) return;

            let rule_arr = rule_str.split('|');
            for (let i = 0; i < rule_arr.length; i++) {
                let item = rule_arr[i];
                let item_arr = item.split(':');
                rule[item_arr[0]] = JSON.parse(item_arr[1]);
            }
        }

        function listen() {
            $ele.on('blur', function () {
                let result = me.validator.is_valid(me.get_ele());
                if (result) {
                    $error_ele.hide();
                } else {
                    $error_ele.show();
                }

                console.log('result', result);
            })

        }

        function get_error_ele() {
            $error_ele = $(get_error_selector());
        }

        function get_error_selector() {
            return '#' + $ele.attr('name') + '-input-error';
        }

        init();

    }
});