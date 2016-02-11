
##### knife setting
```
$ mkdir learn-chef
$ cd ~/learn-chef
$ mkdir .chef
$ cd .chef
$ cp ~/Download/knife.rb .
$ cp ~/Download/kghoon.pem .
$ knife ssl check
```

##### cookbook

```
$ cd ~/learn-chef
$ mkdir cookbooks
$ knife cookbook site download learn_chef_httpd
$ tar xzf learn_chef_httpd-0.2.0.tar.gz -C cookbooks
$ rm learn_chef_httpd-0.2.0.tar.gz
$ knife cookbook upload learn_chef_httpd
$ knife cookbook list
```

##### Setup node using vagrant and virtualbox

```
$ vagrant box add centos-6.5 http://opscode-vm-bento.s3.amazonaws.com/vagrant/virtualbox/opscode_centos-6.5_chef-provisionerless.box
$ vagrant init centos-6.5
$ vagrant up --provider=virtualbox
$ vagrant ssh
```

##### Bootstrap node

```
$ knife bootstrap localhost --ssh-port 2222 --ssh-user vagrant --sudo --identity-file /c/Users/Jihoon\ Kang/centos-6.5/.vagrant/machines/default/virtualbox/private_key --node-name node1 --run-list 'recipe[learn_chef_httpd]'
$ knife node show node1
```

##### Update node configuration

1. Add some modification to to ~/learn-chef/cookbooks/learn_chef_httpd/templates/default/index.html.erb
```
$ vi cookbooks/learn_chef_httpd/templates/default/index.html.erb
```
2. Upload cookbook to chef server 
```
$ knife cookbook upload learn_chef_httpd
```
3. Run cookbook on the node
```
$ knife ssh localhost --ssh-port 2222 'sudo chef-client' --manual-list --ssh-user vagrant --identity-file ~/centos-6.5/.vagrant/machines/default/virtualbox/private_key
```

##### Clean up

```
$ knife node delete node1 --yes
$ knife client delete node1 --yes
```

