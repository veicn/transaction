package com.sinochem.crude.trade.member.controller.vo;

import com.sinochem.crude.trade.member.domain.MemberCredentials;

import java.util.List;

public class SualificationVo {

    public SualificationVo(List<MemberCredentials> credentials){
        this.memberCredentialsList = credentials;
    }

    List<MemberCredentials>  memberCredentialsList ;

    public List<MemberCredentials> getMemberCredentialsList() {
        return memberCredentialsList;
    }

    public void setMemberCredentialsList(List<MemberCredentials> memberCredentialsList) {
        this.memberCredentialsList = memberCredentialsList;
    }

    /**
     *
     * @param code
     * @return 0:没有该资质 1：审核中 2：审核通过 3:驳回 （未实现）
     */
    public int audited(String code){
        for (MemberCredentials memberCredentials : memberCredentialsList){
            if(code.equals( memberCredentials.getCredentialsCode() )){
                if(memberCredentials.getAudit()!=null&&memberCredentials.getAudit()==1){
                    return 2;
                }
                if(memberCredentials.getAudit()!=null&&memberCredentials.getAudit()==2){
                    //return 3;
                    return 0;//TODO 还未增加“驳回”的表形式，先当作“没有资质”处理
                }
                return 1;
            }
        }
        return 0;
    }


}
