#!/usr/bin/ruby -w
# ruby comment too

$global_variable1 = :some_symbol1

class ExampleClass
    # operator overloading
    def <=>(other)
        print "this values are equal"
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
        return (b,c,d, 1...10)
    end
end

BEGIN {
    ## some error cases:
    123abc
    1.02e10e
}