#!/usr/bin/bash

# Install packages
yum -y update
amazon-linux-extras install -y nginx1

# Config and install source code

# S3 environment
CONFIG_BUCKET="s3://edu.au.cc.jzw.image-gallery-config"
aws s3 cp ${CONFIG_BUCKET}/nginx/nginx.conf /etc/nginx/nginx.conf
aws s3 cp ${CONFIG_BUCKET}/nginx/default.d/image_gallery.conf /etc/nginx/default.d/image_gallery.conf

# Start/enable services
systemctl stop postfix
systemctl disable postfix
systemctl start nginx
systemctl enable nginx

