<#assign text="{'name':12,'age':'30+','addr':'上海上海'}" />
<#assign data=text?eval />

data.name=${data.name} <br/>