$(function () {
    'use strict';

    window.Validator = function (val, rule) {

        this.is_valid = function (new_val) {
            let key;
            if (new_val !== undefined)
                val = new_val;

            if (!rule.required && !val) return true;

            for (key in rule) {
                let result = this['validate_' + key]();
                if (!result) {
                    return false;
                }
            }
            return true;
        };


        this.validate_min = function () {
            val = parseFloat(val);
            return val >= rule.min;
        };

        this.validate_max = function () {
            val = parseFloat(val);
            return val <= rule.max;
        };

        this.validate_fixedLength = function () {
            val = val.toString();
            return val.length === rule.fixedLength;
        };

        this.validate_maxlength = function () {
            val = val.toString();
            return val.length <= rule.maxlength;
        };

        this.validate_minlength = function () {
            val = val.toString();
            return val.length >= rule.minlength;
        };

        this.validate_numeric = function () {
            return $.isNumeric(val);
        };

        this.validate_required = function () {
            let real = $.trim(val);
            if (!real && real !== 0) return false;
            return true;
        };

        this.validate_pattern = function () {
            let reg = new RegExp(rule.pattern);
            return reg.test(val);
        };

        this.validate_afterIncludeToday = function () {
            if (!$.isNumeric(val)) {
                val = Date.parse(val);
            }
            let today = new Date();
            today.setHours(0);
            today.setMinutes(0);
            today.setSeconds(0);
            today.setMilliseconds(0);
            return val >= today;
        };

        this.validate_beforeIncludeToday = function () {
            if (!$.isNumeric(val)) {
                val = Date.parse(val);
            }
            let today = new Date();
            today.setHours(0);
            today.setMinutes(0);
            today.setSeconds(0);
            today.setMilliseconds(0);
            return val <= today;
        };
    }
});