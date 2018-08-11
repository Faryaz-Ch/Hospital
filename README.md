# Hospital
The current workflow process for calculating drug dosages are as follows:
1. Drug dosage is required to be administered
2. Nurse checks the following
a. The patients chart and verification
b. The drug to be administered
c. The dosage or standing order given by the Dr or Anesthetist
3. The Nurse then calculates the Dosage and Total Medication as follows:
       a.
For Pediatrics (Children)
i. Verify the drug is for the correct patient
1. For the App record the NHI Number, Name, and Room#
ii. Verify the Drug. The drug will have the Name and Milligrams per Milliliters (mg/ml)
1. Each drug has a standard level of milligrams (Drug Mg) and milliliters (Drug ml) which is used in all calculations
iii. Enter the Standing Order amount from the Anesthetist (mg/kg)
1. The standing order is a number that is calculated by the anesthetist
iv. Enter the Weight (in kgs)
v. Calculate the [Dosage] = [Standing Order] * [Weight]
vi. Calculate the [Total Medication] = ([Dosage] / [Drug Milligrams]) * [Drug Milliliters]
For Adults
i. Verify the drug is for the correct patient
1. For the App record the NHI Number, Name, and Room#
ii. Verify the Drug. The drug will have the Name and Milligrams per Milliliters (mg/ml)
1. Each drug has a standard level of milligrams (Drug Mg) and milliliters (Drug ml) which is used in all calculations
b.
iii. The Dr prescribes the Dosage for the patient (mg)
1. This is a number that has been calculated by the Dr
iv. Calculate the [Total Medication] = ([Dosage] / [Drug Milligrams]) * [Drug Milliliters]
4. Calculations are then rounded up or down – if it is 0.5 or greater, then it is rounded up else it is rounded down
5. When displaying the medication to be administered it should show the following: a. The drug to be administered
b. The calculated amount
c. The amount to be administered (after rounding)
d. Include all units of measurements
6. A separate calculation is performed by another nurse and calculations are checked against each other and verified IF AND ONLY IF the drug is a Dangerous Drug (DD) or Intravenous Drug. Both nurses sign the chart to confirm dosages and total medication
7. The Total Medication is then given to the patient
8. The medication is recorded on the Patients drug chart
9. When medications are given it is time-stamped
