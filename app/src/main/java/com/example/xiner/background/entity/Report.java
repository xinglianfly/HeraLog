package com.example.xiner.background.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by seald on 2017/12/7.
 */

public class Report  implements Serializable{
   public List<EditMaterial> getEntryEdit() {
      return entryEdit;
   }

   public void setEntryEdit(List<EditMaterial> entryEdit) {
      this.entryEdit = entryEdit;
   }

   private List<EditMaterial> entryEdit;
   private List<Materials> entryAdd;



   public List<Materials> getEntryAdd() {
      return entryAdd;
   }

   public void setEntryAdd(List<Materials> entryAdd) {
      this.entryAdd = entryAdd;
   }

   public List<Materials> getEntryRemove() {
      return entryRemove;
   }

   public void setEntryRemove(List<Materials> entryRemove) {
      this.entryRemove = entryRemove;
   }

   public List<Editinfo> getRecordEdit() {
      return recordEdit;
   }



   public void setRecordEdit(List<Editinfo> recordEdit) {
      this.recordEdit = recordEdit;
   }

   @Override
   public String toString() {
      return "Report{" +
              "entryEdit=" + entryEdit +
              ", entryAdd=" + entryAdd +
              ", entryRemove=" + entryRemove +
              ", recordEdit=" + recordEdit +
              ", order='" + order + '\'' +
              ", number='" + number + '\'' +
              '}';
   }

   public String getOrder() {
      return order;
   }

   public void setOrder(String order) {
      this.order = order;
   }

   private List<Materials> entryRemove;
   private List<Editinfo>recordEdit;
   private String order;

   public String getNumber() {
      return number;
   }

   public void setNumber(String number) {
      this.number = number;
   }

   private String number;



}
