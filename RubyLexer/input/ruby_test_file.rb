#!/usr/bin/ruby -w

# include is not a ruby keyword
include  Some_lib

$global_variable1 = :some_symbol1

$a  = [1,2,3,"4",:5]

class ExampleClass
    # operator overloading
    def <=>(other)
        print "overload equal"
        @instance_variable += 10
        _local_variable -= -15
        return true
    end

    def no_args
        puts :no_args_function
        @@class_variable = 3.1415;
        print 102.to_c # print complex form
    end

    def multiple_args(a1, a2, a3, a4, a5)
        b = a1 + a2 - a3*a4/a5 % 2
        c = -3
        d = +256E-10 + 10e120 - 0.24 + 0.04 - 0.
    end

    def exclamation
        r = some_lib.eql?(2.0)
        s = "UPpercase"\
            "MULTILINE string"\

            'values'
        s.downcase!
    end
end

module This_lib
p <<END_SQL.gsub(/\s+/, " ").strip
    SELECT * FROM     users
             ORDER BY users.id DESC
END_SQL
end


BEGIN {
    ## some error cases:
    wrong_identifier_or_number = 123abc
    wrong_exp_representation = 1.02e10e-11

    single_lined_multiline_comment = "some"\"errors"

    identifier_between = "some"\
    a "errors"

    not_continuation = 'dsdsds'\
}

