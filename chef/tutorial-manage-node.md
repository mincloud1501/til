
##### knife setting
$ mkdir learn-chef
$ cd ~/learn-chef
$ mkdir .chef
$ cd .chef
$ cp ~/Download/knife.rb .
$ cp ~/Download/kghoon.pem .
$ knife ssl check

##### cookbook
$ cd ~/learn-chef
$ mkdir cookbooks
$ knife cookbook site download learn_chef_httpd
$ tar xzf learn_chef_httpd-0.2.0.tar.gz -C cookbooks
$ rm learn_chef_httpd-0.2.0.tar.gz
$ knife cookbook upload learn_chef_httpd
$ knife cookbook list
