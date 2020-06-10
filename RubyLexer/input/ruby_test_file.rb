"\n"

dklsajdkljsakljdskl47378473289743279473297432749832749832798
djsjiowqjiodwjqdiowjqidjwqio#flkdjskldsjkfljdslkfjdskljflkds
jkdsajkfdjsklfjkdlsjfkljkdls$fjklddsadads
djksakjldjksaljkaklsd%dopsadsadasdsad
jdskajkdjasdsadasdsad12321312312312321_
4324234423423.4324324234234324324234423423
4789327849723894732878423.1321321321.
4789327849723894732878423.1321321321.432423423432432342
+++
+++++
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