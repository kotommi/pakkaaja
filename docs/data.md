### tarinat.txt
time java -jar pakkaaja.jar -c tarinat.txt
real 0m0,140s/user	0m0,187s/sys	0m0,024s
Koko: 130683
suhde: 59.39%
time java -jar pakkaaja.jar -x tarinat.txt.huf
real 0,134s/user 0,182s/sys	0,025s

time gzip tarinat.txt
Koko: 87867
real 0,020s/user 0,016s/sys	0,004s
suhde: 39.93%
time gzip -d tarinat.txt.gz
real 0,006s/user 0,006s/sys	0,000s

time compress tarinat.txt
real 0,010s/user 0,007s/sys 0,003s
Koko: 99433
suhde: 45.19%
time uncompress tarinat.txt.Z
real 0,007s/user 0,005s/sys 0,000s

### atomic.mpg
time java -jar compress-1.0-SNAPSHOT-all.jar -c atomic.mpg
real 0,569s/user 0,451s/sys 0,069s
Koko: 9028387
suhde: 98.79%
time java -jar compress-1.0-SNAPSHOT-all.jar -x atomic.mpg.huf
real 0,863s/user 0,702s/sys	0,052s

time gzip atomic.mpg
real 0,298s/user 0,294s/sys 0,004s
koko: 8491467
suhde: 92.92%
time gzip -d atomic.mpg.gz
real 0,072s/user 0,068s/sys	0,004s

time compress -f atomic.mpg
real 0,160s/user 0,152s/sys 0,008s
Koko: 10298565
suhde: 112.69%
time uncompress atomic.mpg.Z
real 0,067s/user 0,051s/sys 0,017s


