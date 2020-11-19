library(ggplot2)
library(readr)

#getcwd()
#iterFiles <- list.files(".", pattern="IterationInfo*")
#"IterationINfo.0.R0.txt"

plotMetric <- function( filename ){
  dat <- read_tsv(filename)
  colnames(dat)[1]="Iteration"
  colnames(dat)[2]="Metric"
  ggplot( data=dat, mapping=aes(x=`Iteration`,y=`Metric`)) + geom_line()
}

