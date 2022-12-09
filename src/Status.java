public class Status
{

   private int _statusID;
   private String _statusName;
   private String _statusDescription;
   private int _active;
   private String _dateAdded;
   private String _dateModified;

   public int getStatusID()
   {
       return _statusID;
   }
   public void setStatus(int statusID)
   {
       _statusID = statusID;
   }

   public String getStatusName()
   {
       return _statusName;
   }
   public void setStatusName(String statusName)
   {
       _statusName = statusName;
   }
   public int getActive()
   {
       return _active;
   }
   public void setActive(int active)
   {
       _active = active;
   }
   public String getDateAdded()
   {
       return _dateAdded;
   }
   public void setDateAdded(String dateAdded)
   {
       _dateAdded = dateAdded;
   }
   public String getDateModified()
   {
       return _dateModified;
   }
   public void setDateModified(String dateModified)
   {
       _dateModified = dateModified;
   }

}
