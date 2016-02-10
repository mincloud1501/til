#### CentOS7에 Chef Development Kit 설치하기

* 다운로드 : https://downloads.chef.io/chef-dk/redhat/

##### 1. Chef DK 다운로드 받기 

```
$ curl -O https://opscode-omnibus-packages.s3.amazonaws.com/el/7/x86_64/chefdk-0.10.0-1.el7.x86_64.rpm
```

##### 2. 설치하기

```
$ rpm -i chefdk-0.10.0-1.el7.x86_64.rpm

warning: chefdk-0.10.0-1.el7.x86_64.rpm: Header V4 DSA/SHA1 Signature, key ID 83ef826a: NOKEY
Thank you for installing Chef Development Kit!
```

##### 3. 확인

```
$ chef

Usage:                                                                                                
    chef -h/--help                                                                                    
    chef -v/--version                                                                                 
    chef command [arguments...] [options...]                                                          
                                                                                                      
                                                                                                      
Available Commands:                                                                                   
    exec                    Runs the command in context of the embedded ruby                          
    env                     Prints environment variables used by ChefDK                               
    gem                     Runs the `gem` command in context of the embedded ruby                    
    generate                Generate a new app, cookbook, or component                                
    shell-init              Initialize your shell to use ChefDK as your primary ruby                  
    install                 Install cookbooks from a Policyfile and generate a locked cookbook set    
    update                  Updates a Policyfile.lock.json with latest run_list and cookbooks         
    push                    Push a local policy lock to a policy group on the server                  
    push-archive            Push a policy archive to a policy group on the server                     
    show-policy             Show policyfile objects on you Chef Server                                
    diff                    Generate an itemized diff of two Policyfile lock documents                
    provision               Provision VMs and clusters via cookbook                                   
    export                  Export a policy lock as a Chef Zero code repo                             
    clean-policy-revisions  Delete unused policy revisions on the server                              
    clean-policy-cookbooks  Delete unused policyfile cookbooks on the server                          
    delete-policy-group     Delete a policy group on the server                                       
    delete-policy           Delete all revisions of a policy on the server                            
    undelete                Undo a delete command                                                     
    verify                  Test the embedded ChefDK applications                                     
```

