# PROJECT NAME

## Project Description

Employees of the Celeste Noir delivery system now have a way to apply for reimbursements for expences accrued while on the job.
  Employees may make a new account, file for a reimbursements, and see their reimbursement history.
  Financial managers may review, approve and deny pending reimbursements, as well as see the history of all reimbursements. 
    To accuire a new financial manager's account, please contact our technical department. 

## Technologies Used

* Java - version 8
* PostreSQL - version 42.2.14
* Apache-Tomcat - version 9.0.27

## Features

* Employees can make a new account
* Employees can submit a new reimbursement 
* Financial managers can review, approve, and deny pending reimbursements
* Passwords are hashed in the database, so your credentials are kept safe


To-do list:
* Add window alert for when a user successfully creates an account
* Employees will be able to upload a reciept for the reibursement request
* Employees will recieve an email to their company address informing them of a new account being created, and giving them their temporary password

## Getting Started
   
* `git clone https://github.com/kMaths/CelesteNoir.git`
* Enter as environmental variables to connect to your own PostgreSQL database:
    > `url` for your database url  
    > `psw` for your password  
    > `usr` for your username  
    > `drv` for your driver  
* Execute the initialization_script.sql and instantiation_script.sql found in CelesteNoir/src/main/resources to set up the database
* Start your local server and navigate to `http://localhost:{your port}/CelesteNoir`


## Usage

> The page you land on

![LandingPage](https://user-images.githubusercontent.com/15041251/99195737-3def7380-274d-11eb-80a7-707e38524e50.png)

> Create a new account: Note the page does not alert you when your account is successfully created

![Register](https://user-images.githubusercontent.com/15041251/99195788-8b6be080-274d-11eb-860e-3a47da8ef77e.png)

> Refresh the page and log in.

![Login](https://user-images.githubusercontent.com/15041251/99195793-9c1c5680-274d-11eb-8f77-1e485e464d4d.png)


> Right now you do not have any reimbursements pending. Create a new one.

![NewReimbursement](https://user-images.githubusercontent.com/15041251/99195817-b7876180-274d-11eb-9c97-9d8c6d1a2bea.png)

> Log back in to see your reimbursement

![SeeNewReimbursement](https://user-images.githubusercontent.com/15041251/99195830-c8d06e00-274d-11eb-9bb2-3d23dcd3def4.png)


## License

This project uses the following license: [MIT License](<https://www.mit.edu/~amini/LICENSE.md>).
