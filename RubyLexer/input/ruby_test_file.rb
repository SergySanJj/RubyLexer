"\n"
"smf"\"dsd"


dklsajdkljsakljdskl4737
jqidjwqio#flkdjsklds
kdls$fjkld
aklsd%dopsadsadas
sadasdsad12321312312312321_
4324234423423.43243242
4789327849723894732878423.1321321321.
4789327849723894732878423.1321321321.432423423432432342
+++
+++++
===
====
=====
'dkslajkdjskaljdklsajdkljaskljsa%432324&&%^2fd
"dkslajkdjskaljdklsajdkljaskljsa%432324&&%^2fd
"fjkdsjkfdsjhfj\nhdsfjhdfs.4732479'327847832789432jfkdsjfkldsjfkldsjfdkls"


123dsadsa
dsadsa32132
1.22332e-12323mta
1.22332e-12323
"dsadsadsadsa

require "pry"


@sydney = {
  'Hornsby' => ['Epping', 'Chatswood', 'Brooklyn'],
  'Epping' => ['Hornsby', 'Chatswood', 'Strathfield'],
  'Chatswood' => ['Hornsby', 'Epping', 'Central'],
  'Strathfield' => ['Lidcombe', 'Epping', 'Central'],
  'Bankstown' => ['Sydneham', 'Liverpool', 'Lidcombe'],
  'Liverpool' => ['Blacktown', 'Bankstown', 'Lidcombe', 'Campbelltown'],
  'Sydneham' => ['Campbelltown', 'Bankstown','Central'],
  'Central' => ['Chatswood', 'Strathfield', 'Sydneham'],
  'Brooklyn' => ['Hornsby'],
  'Campbelltown' => ['Liverpool','Sydneham'],
  'Lidcombe' => ['Liverpool','Bankstown', 'Strathfield'],
  'Blacktown' => ['Liverpool', 'Penrith', 'Richmond'],
  'Richmond' => ['Blacktown'],
  'Penrith' => ['Blacktown']
} # end of stations hash


# p shortest_path "Grand Central", "Times Square", @mta
# p shortest_path "Penrith", "Brooklyn", @sydney
p shortest_path "Epping", "Liverpool", @sydney

#!/usr/bin/ruby -w
# some comment
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
        d = +256E-10 + 10e-120 - 0.24 + 0.04 - 0.
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
    single_slash_in_literal = "some string\_no other slash"
    not_continuation = 'dsdsds'\
}