class Poly{
  private class Term{
    private int coef;
    private int expo;
    private Term next;
    public Term(int coef,int expo,Term next){
      this.coef=coef;
      this.expo=expo;
      this.next=next;
    }
  }
  private Term head;
  public Poly(){
     head = new Term(0,0,null);// makes a new polynomial with no term's.
   }
  public Poly term(int coef, int expo){
    if (expo<0){
      throw new  IllegalArgumentException();// if negative then throw an illrgalArgumnet.
    }else{
      Term left = head;
      Term right = left.next;
      if(head.next==null){
        head.next = new Term(coef,expo,null);// no terms then add first term.
      }else{
        while(right!= null){
          if(right.expo<expo){// visit a Term whose expo slot is less than the parameter expo, then add the new Term immediately before that Term,
            left.next = new Term(coef,expo,right);
            break;
          }else if (right.expo>expo){//visit a Term whose expo slot is greater than the parameter expo, then skip that Term and go on to the next one.
            left = right;
            right = right.next;
          }else if (right.expo == expo){// visit a Term whose expo slot equals the parameter expo, then throw an IllegalArgumentException
            throw new  IllegalArgumentException();
          }
        }
        if(right==null){// check the last one.
          left.next = new Term(coef,expo,null);
        }
      }
    }
    return this;
  }
  public Poly plus(Poly that){//new polynomial with no Term’s.
    Poly a = new Poly();
    Term T = this.head.next;
    while(T!=null){//visit each Term in this. 
      a.term(T.coef,T.expo);//add it to the new polynomial by calling term.
      T=T.next;
    }
    Term S = that.head.next;
    while(S!=null){//visit each Term in the polynomial that.
      a.add(S.coef,S.expo);// add it to the new polynomial by calling add. 
      S=S.next;
    }
    return a;
  }
  public Poly minus(){
   Poly a = new Poly();//make a new polynomial with no Term’s.
   Term T = this.head.next;
   while(T!=null){//Each time you visit a Term, add a new Term to the new polynomial by calling term.
     a.term(-T.coef,T.expo);
     T=T.next;
   }
   return a;
  }
  public void add(int coef,int expo){
    Term left = this.head;
    Term right = left.next;
    if(expo<0){//expo is negative, then throw an IllegalArgumentException.
       throw new  IllegalArgumentException();
    }else{
      if(head.next==null){// no term then add an new term.
        head.next = new Term(coef,expo,null);
      }else{
          while(right!=null){
            if(right.expo<expo){//a Term whose expo slot is less than the parameter expo, then add a new Term immediately before that Term,
              left.next = new Term(coef,expo,right);
              return;
            }else if(right.expo>expo){// a Term whose expo slot is greater than the parameter expo, then skip that Term and go on to the next one. 
              left = right;
              right = right.next;
            }else if(right.expo==expo){// a Term whose expo slot equals the parameter expo, then add the parameter coef to that Term’s coef slot.
              right.coef+=coef;
              break;
            }
          }
          if(right == null){// check the last one.
            if(left.expo>expo){
            left.next = new Term(coef,expo,null);
            }
          }
        }
    } 
    left = this.head;
    right= left.next;
      while(right!=null){
        if(right.coef==0){// check the '0's.
          left.next = right.next;
          break;
        }else{
        left = right;
        right = right.next;
        }
      }
  }
  public String toString(){
    StringBuilder str = new StringBuilder();
    Term T =head.next;
    if(T==null){
     return "0";
    }
    str.append(T.coef);
    str.append("x");
    appendExponent(str,T.expo);
    T = T.next;
    while(T!=null){
      if(T.coef>0){
        str.append("+");
        str.append(T.coef);
        str.append("x");
        appendExponent(str,T.expo);
      }else{
        str.append(T.coef);
        str.append("x");
        appendExponent(str,T.expo);
      }
      T=T.next;
    }
    String a = str.toString();
    return a;
  }
   private void appendExponent(StringBuilder builder, int expo)
  {
    if (expo < 0)
    {
      throw new IllegalStateException("Bad exponent " + expo + ".");
    }
    else if (expo == 0)
    {
      builder.append('⁰');
    }
    else
    {
      appendingExponent(builder, expo);
    }
  }

//  APPENDING EXPONENT. Do all the work for APPEND EXPONENT when EXPO is not 0.

  private void appendingExponent(StringBuilder builder, int expo)
  {
    if (expo > 0)
    {
      appendingExponent(builder, expo / 10);
      builder.append("⁰¹²³⁴⁵⁶⁷⁸⁹".charAt(expo % 10));
    }
  }
   public static void main(String[] args) 
      { 
        Poly p0 = new Poly(); 
        Poly p1 = new Poly().term(1, 3).term(1, 1).term(1, 2); 
        Poly p2 = new Poly().term(2, 1).term(3, 2); 
        Poly p3 = p2.minus(); 
        Poly p4 = p0.minus();
        Poly p5 = new Poly().term(-2,1);
        Poly p6 = new Poly().term(1,6);
      
        
     
        System.out.println(p0);           //  0 
        System.out.println(p1);           //  1x3 + 1x2 + 1x1 
        System.out.println(p2);           //  3x2 + 2x1 
        System.out.println(p3);           //  −3x2 − 2x1 
        System.out.println(p4);           //  0
        System.out.println(p6.term(1,5)); // 1x⁶+1x⁵
        
 
     
        System.out.println(p1.plus(p2));  //  1x3 + 4x2 + 3x1 
        System.out.println(p1.plus(p3));  //  1x3 − 2x2 − 1x1 
        System.out.println(p0.plus(p1));  //  1x3 + 1x2 + 1x1 
        System.out.println(p0.plus(p0));  //  0
        System.out.println(p0.plus(p3));  //  −3x2 − 2x1 
        System.out.println(p1.plus(p5));  //  1x³+1x²-1x¹
        System.out.println(p2.plus(p5));  //  3x²
        
      } 
}
