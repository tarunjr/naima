# naima
##Naima: Not Another Intelligent Medical Assistant   

#### Developed @ SequoiaHack::2016 by [Prashant KS](https://github.com/tarunjr/) , [Saurabh Minni](https://github.com/tarunjr/) and [Tarun Rathor](https://github.com/tarunjr/)

##Vision: 
Provide assisted expert health care to remote patients in an easy and fast way.

##Problem: 
In indian rural and semi-urban context access to quality health care is very sparse. 
Locally available doctors may not be most experienced or informed to evaluate patients medical 
condition with consistency and quality.

##Solution: 
We propose to empower the doctor/ care agents at rural health centers with a system, that :-
* Guide them collect most relevant examination data for the medical condition.
* Enable online review of the case with a relevant speciality expert.

System improves over time in following manner:-
* Experts update the system with more knowledge as they review the cases.
* Latest medical data curated periodically to improve examination process.
* Treatment outcomes will be feedback to the system in near realitime to finetune and localize.

## System Architecture

### Data Design
Following domain entities will be modelled in the system.
* Patient : A human who has the medical condition
* Doctor: A qualified doctor who can administer treatment.
* Care Provider: A trained human who can collect diagnostic data. E.g Nurse, Trainee
* Symptom: An indicator of a medical condition. E.g Bloating, Burning
* Test: A medical diagnostic test done in lab. E.g Blood Test
* Condition: A medical situation, disease, allergy, reaction or complication. E.g  Acidity
* Speciality: A segment of medical expertise. E.g  Neurology, Gastroenterology etc.
* Treatment: A prescribed drug, therapy or action which will address the condition
* Case: A record which which details the diagnosis and treatment of patient condition.

### Service Design

### Analytics Design

### Application Design
