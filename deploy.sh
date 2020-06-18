#!/usr/bin/bash

# Deploy Application server
if [ "$#" != "1" ]; then
	echo "Usage: deploy <version-number> example: 1.0"
	exit 1
fi

cd ec2-scripts
rm -f ec2-prod-latest.sh
ln -s ec2-prod-$1.sh ec2-prod-latest.sh
ln -s ec2-nginx-$1.sh ec2-nginx-latest.sh
cd ..

CONFIG_BUCKET="s3://edu.au.cc.jzw.image-gallery-config"

aws s3 cp ec2-scripts/ec2-prod-$1.sh ${CONFIG_BUCKET}
aws s3 cp ec2-scripts/ec2-nginx-$1.sh ${CONFIG_BUCKET}
aws s3 cp ec2-scripts/ec2-prod-latest.sh ${CONFIG_BUCKET}
aws s3 cp ec2-scripts/ec2-nginx-latest.sh ${CONFIG_BUCKET}
aws s3 cp /etc/nginx/nginx.conf ${CONFIG_BUCKET}/nginx/nginx.conf
aws s3 cp /etc/nginx/default.d/image_gallery.conf ${CONFIG_BUCKET}/nginx/default.d/image_gallery.conf
