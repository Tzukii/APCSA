class Chick implements Animal {
   private String myType;
   private String mySound;
   private String mySound2;

   public Chick(String type, String sound) {
      myType = type;
      mySound = sound;
   }

   public Chick(String type, String sound1, String sound2) {
      myType = type;

      mySound = sound1;
      mySound2 = sound2;
   }

   public String getSound() {
      double rand = Math.random();

      if (rand < 0.5) {
         return mySound;
      }
      return mySound2;

   }

   public String getType() {
      return myType;
   }
}