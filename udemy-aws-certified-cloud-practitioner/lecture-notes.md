# What is cloud computing?

- Cloud computing is the on-demand delivery of compute power, database, storage, applications, network connections, and other IT resources.
- Through a services platform with pay-as-you-go pricing
- You can provision exactly the right type and size of computing resources you need
- You can extend as many resources as you need, almost instantly
- You can access the provisioned resources easily

## Deployment Models of the Cloud

- Private cloud
  - cloud services used by a single organization, not exposed to public
  - Security for sensitive appliations
- Public cloud
- Hybrid

## Five Characteristics of CC

1. On-demand self service
   - Users can provision resources and use them without human interaction from the service provider 
2. Broad network access
3. Multi-tenancy and resource pooling
   - Multiple customers can share the same infrastructure and applications, served by the same physical resources, with security and privacy 
4. Rapid elasticity and scability
   - automatically and quickly scale, acquiring or disposing, the resources in need
5. Measured service
   - Usage is measured, priced correctly
  
## Six Advantages of CC

1. Trade CAPEX for OPEX
2. Benefit from massive economies of scale: cloud provider make things more efficient due to large scale
3. Stop guessing capacity: measured usage
4. Increase speed and agility
5. Stop spending money running and maintaining data centers
6. Go to market quicker

## Types of CC

- Infrastructure as a Service (IaaS)
  - Networking, computers, data storage
- Platform as a Service (PaaS)
  - Elastic Beanstack
- Software as a Service (SaaS)

### AWS pricing

- Compute: pay for compute time
- Storage: pay for data stored in the cloud
- Data transfer Out of the cloud: data In is free

# Aws Overview

## Region
- a cluster of data centers
### How to choose an AWS region
- Compliance: data governance and legal requirements
- Proximity to customers: reduce latency
- Available services: services and features are not the same in different regions
- Pricing: different

## Availability Zones

- Each region has many availability zones, 3-6.
- Each availability zone is one ore more discrete data centers with redundant power, networking and connectivity
- They are seperate from each other so that they are isolated from disasters
- They are connected with high bandwidth, ultra-low latency networking

## Points of Presence (Edge Locations)

# IAM - Identity and Access Management

- Root account: created by default, should not be used and shared
- Users: are people within your organization
- Groups: contain users, not other groups
- Users can belong to multiple groups, or not


## IAM users & groups Hands on

1. Login as Root account -> access IAM service
2. Create a new IAM user -> create a new group as admin with AdministratorAccess policy
3. Set the account login alias
4. Login with the new created IAM user

## Permissions

Users and Groups can be assigned Json documents call policies

## Policy inheritance

Policies can be assigned to an user
- directly
- or from groups that user belongs to

## MFA

## AWS management

- AWS CLI
- AWS SDK (used in apps, supported in different programming languages)
- AWS CloudShell: terminal with AWS cli

## IAM Roles for Services

AWS Services will need to perform actions on your behalf

### Hands-on: Create Role for EC2 service

1. Access IAM service -> Roles -> Create Role
2. Trust entity type: AWS service, Use case: EC2
3. Add permissions
   Add policies: IAMReadOnlyAccess for example

## IAM Security Tools

- IAM credentials Report (account level)
  - a report that list all account's users and the status of their credentials
  - IAM -> Access reports -> Credential report
- IAM Access Advisor
  - shows the service permissions granted to a user and when those services were last accessed
  - IAM -> Users -> Access Advisor

## Shared Responsibility Model for IAM


# EC2 - Elastic Compute Cloud

- Renting Virtual Machines (EC2 instances)
- Storing data on virtual devices (EBS)
- Distributing load across machines
- Scaling the services using auto-scaling groups (ASG)

## EC2 sizing & configuration options

- OS: Linux, Windows, MacOS
- How much compute power & cores (CPU)
- How much RAM
- How much storage space
  - Network attached (EBS & EFS)
  - hardware (local disk)
- Network card: speed, public IP address
- Firewall rules
- Boostrap script: EC2 User Data

## EC2 Instance Types

- General Purpose
  - Balance:
    - Compute
    - Memory
    - Network
- Compute Optimized: tasks require high performance processors (C5, C6, ...)
  - HCP
  - Machine learning, scientific modeling
  - Gaming servers
- Memory Optimized: large dataset in memory
  - Distributed cache stores
  - In-me databases for BI
  - real-time processing of big data
- Storage optimized (I, D, H classes): tasks that require high, sequential R/W access to datasets on local storage
  - OLTP systems
  - SQL, NoSQL databases
  - Cache for in-memory databases
  - Data warehousing applications
  - Distributed file systems

## Security Groups

- EC2 instances can be attached to Security groups, each has Inbound and Outbound rules
- Security group can reference each others


# EBS overview

- An EBS (Elastic Block Store) volume is a network drive you can attach to your instances while they are running
- To persist data, even after termination
- Can only mounted to 1 EC2 instance at a time
- Bound to a specific availability zone
- Think of them as a "network USB stick"
- Can be detached from 1 instance to attach to anothe
- Free tier: 30G of free per month

## EBS snapshots

- Make a backup of ESB volume at a point of time
- No need to detach to do snapshot but recommended
- Can copy snapshots across AZ or Region
- Snapshots can be moved to "archive tier" that is 70% cheaper, but longer to restore.

# AMI - Amazon Machine Images

- AMI are a customization of an EC2 instance
  - You add your own software, config, OS, ..
- AMI are built for a specific region (copied across regions)
- You can launch EC2 instances from:
  - A public AMI: aws provided
  - your own AMI: you make and maintain
  - AWS marketplace AMI: made by someone else

## AMI process

- start an EC2 instance and customize it
- stop the instance (data integrity)
- build an AMI -> EBS snapshot
- launch instances from the AMI

## EC2 image builder

Automation of creating, maintaining, validating, testing EC2 AMIs

# EC2 Instance Store

- EBS volumes are good, but "limited" performance
- EC2 Instance store: hard drive of EC2 instnaces
  - better I/O performance
  - lost if EC2 instance terminates
  - good usecases: cache, temporal data

# EFS - Elastic File system

- Managed NFS (network FS), can be mounted to 100s EC2 instances
- Highly available, scalable, expensive but pay per use without capacity planing
- works with EC2 instances in multi AZ

## EFS Infrequent Access (EFS-IA)
- Cheaper
- not frequently accessed files

# FSx Overview

- third-party high performance file system on AWS
- fully managed service
  - FSx for Lustre
  - FSx for Window File Server
  - FSx for NetApp ONTAP

# Elastic Load Balancing

## Scalability & High Availability

### Scalability

- Vertical Scalability: Increasing the size of the compute instance
- Horizal Scalability: Increasing the number of instances/systems for the application

### High Availability
- Running the application in at least 2 Availability Zones
- The goal is to survive during disasters

## Load Balancing

- Load balancers are servers that forward internet traffic to multiple servers (EC2 instances)
- ELB is a managed load balancer
- 4 types
  - Application LB (HTTP/HTTPs/gRPC)
  - Network LB (layer 4, TCP/UDP): ultra-high performance
  - Gateway LB (layer 3)
  - Classic LB: retired in 2023

## Auto Scaling Group

- Scale out (add EC2 instances) to match an increased load
- Scale in (remove EC2 instances) to match a deceased load
- Replace unhealthy instances


# S3 overview

- infinite scaling storage

## Usecases
- backup and storage
- disaster recovery
- archive
- hybrid cloud storage
- application hosting
- media hosting
- big data analytics
- software delivery
- static website

## S3 buckets

- must have globally unique name (across all regions, all accounts)
- defined at the region level
- name has some convention

## S3 objects

- objects (files) have a key, which is the full path
- max size is 5TB
- if uploading more than 5GB, must use "multi-part upload"
- metadata: key-value pairs of system or user metadata
- tags: unicode key-value pair (up to 10) for security, lifecycle
- version id: if versioning enabled

## S3 Security

- User-based: IAM policies - which API calls allowed for a specific user from IAM
- Resource-based:
  - Bucket Policies: allow cross account
- Encryption: objects can be encrypted
- an IAM principal can access an S3 object if
  - the user's IAM permission allow it or the resource policy allow it
  - AND there's no explicit deny

### Bucket policies

```json
{
  "Version": "2012-10-27",
  "Statement": [
    "Sid": "PublicRead",
    "Effect": "Allow",
    "Principal": "*",
    "Action": [
      "s3:GetObject"
    ],
    "Resource": [
      "arn:aws:s3:::examplebucket/*"
    ]
  ]
}
```

## S3 Versioning

## S3 Replication

- Source and Destination buckets must enable Versioning
- Cross-Region Rep (CRR)
- Same-Region Rep (SRR)
- Buckets can be in different accounts
- Copying is asynchronous
- Must have proper IAM permissions

## S3 storage classes

- S3 standard - general purpose
- S3 standard - infrequent access (IA)
- S3 one zone - infrequent access
- S3 glacier instant retrieval
- S3 glacier flexible retrieval
- S3 glacier deep archive
- S3 Intelligent tiering

we can move between classes manually or using S3 lifecycle configurations

### S3 Durability & Availability

- High durability: 10M objects stored -> expectation of 1 object lost during 10K years
- Availability: depend on S3 class
  - S3 standar general purpose: 99.99%


# AWS Snow Family

- collect and process data at the edge
  - snowcome
  - snowball edge
- migrate data IN and OUT of AWS
  - snowcone
  - snowball edge
  - snowmobile

## Data Migrations with Aws Snow family

- limited connectivity
- limited bandwidth
- connection stability

=> It takes longtime, weeks, to transfer data over the network
=> Snow Family: offline devices to perform data migrations

## Edge Computing

- limited/no internet access
- limited computing power
- usecases:
  - preprocess data
  - machine learning at the edge


# Hybrid Cloud storage

Due to security and compliance requirements

- Part of the infrastructure is on-prems
- Part of the infrastructure is on-the-cloud

To expose S3 data on-premises: Storage gateway


# Databases & Analytics

- Data would need indexes to efficiently query/search
- Can define relationships between datasets

=> Databases are optimized for a purpose

## Relational Databases

## NoSQL Databases



