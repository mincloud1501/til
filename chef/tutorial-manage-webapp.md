## Learn to manage a basic web application

##### Setup chef repo

```
$ chef generate repo ~/chef-repo
$ cd ~/chef-repo
$ mkdir .chef
$ cp ~/learn-chef/.chef/knife.rb .chef/
$ cp ~/learn-chef/.chef/kghoon.pem .chef/
$ knife ssl check
$ git add .
$ git commit -m "Initial import"
```

##### Generate a cookbook

```
$ chef generate cookbook cookbooks/awesome_customers
```

##### Generate a recipe

```
$ chef generate recipe cookbooks/awesome_customers user
```

##### Generate a attribute

```
$ chef generate attribute cookbooks/awesome_customers default
```

##### Find latest version of cookbook on site

```
$ knife cookbook site show <cookbook> | grep latest_version
```

##### Generate a configuration file

```
$ chef generate template cookbooks/awesome_customers customers.conf
```

##### Use Berkshelf to install dependencies

```
$ cd ./cookbooks/awesome_customers
$ berks install
```

##### Use Berkshelf to upload the cookbooks to the chef server

```
$ berks upload
```

##### Verify that the upload process succeeded

```
$ knife cookbook list
```