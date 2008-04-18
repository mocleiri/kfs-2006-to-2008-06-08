#! /usr/bin/perl  -w
#
# Copyright 2008 The Kuali Foundation.
# 
# Licensed under the Educational Community License, Version 1.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
# 
# http://www.opensource.org/licenses/ecl1.php
# 
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
# DO NOT add comments before the blank line below, or they will disappear.

open ATTRIB, "Attributes.xml";
$hdrx = <<END;
<?xml version="1.0" encoding="UTF-8"?>
<data xmlns="ns:workflow" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="ns:workflow resource:WorkflowData">
  <ruleAttributes xmlns="ns:workflow/RuleAttribute" xsi:schemaLocation="ns:workflow/RuleAttribute resource:RuleAttribute">
END
$tlx = <<END;
    </ruleAttribute>
  </ruleAttributes>
</data>
END

$flag=0;
$tempPage="";
while ($_ = <ATTRIB>){
	if (/\<ruleAttribute\>/) {
		$flag=1;
		$tempPage = $tempPage.$hdrx;
	}
	if (/\<\/ruleAttribute\>/) {
		$flag=0;
		$tempPage = $tempPage.$tlx;
		open OUTFILE, '>', $name.".xml" or die "Couldn't open ".$name.".xml";
		print OUTFILE $tempPage;
		close OUTFILE;
		$tempPage="";
		print $name.".xml created\n";
		$name="blank";
	}
	if (/\<name\>/){
		if ($name eq "blank"){
			/\>([^<]*)\</;
			$name=$1;
		}
	}
	if ($flag==1) {$tempPage = $tempPage.$_;}
}