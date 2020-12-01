FROM ubuntu:20.04

USER root

RUN apt-get update && apt-get upgrade && apt-get install wget git libgomp1 -y

RUN wget https://github.com/SuperElastix/elastix/releases/download/5.0.1/elastix-5.0.1-linux.tar.bz2

RUN tar -xvf elastix-5.0.1-linux.tar.bz2 -C /

RUN git clone https://github.com/bogovicj/registrationWorkflows_i2k_2020.git


ENV LD_LIBRARY_PATH="/elastix-5.0.1-linux/lib:${PATH}"
ENV PATH="/elastix-5.0.1-linux/bin:${PATH}"
