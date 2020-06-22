#!/usr/bin/bash

# Install packages
yum -y update
yum install -y tree java-11-openjdk-devel git
amazon-linux-extras install -y java-openjdk11

su ec2-user -l -c 'curl -s "https://get.sdkman.io" | bash && source .bashrc && sdk install gradle'

# Config and install source code
cd /home/ec2-user
git clone https://github.com/jesselwade/java-image-gallery.git
chown -R ec2-user:ec2-user java-image-gallery

# Start/enable services
systemctl stop postfix
systemctl disable postfix

su ec2-user -l -c 'cd ~/java-image-gallery && ./start' >/var/log/image_gallery.log 2>&1 &
