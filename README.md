Welcome to the ButtonAnimProgress wiki!

### Add dependency

implementation "com.github.dips25:ButtonAnimProgress:v1.3"

### Add in layout

`* <com.example.buttonprogressbar.ButtonProgress`
*         `android:layout_width="match_parent"`
*         `android:layout_height="50dp"`
*         `app:layout_constraintTop_toTopOf="parent"`
*         `android:layout_margin="20dp"`
*         `app:text="Click"`
*         `app:textColor="@color/white"`
*         `app:textSize="12"`
*         `app:backgroundColor="@android:color/holo_green_dark"`
*         `app:progressBackground="@android:color/holo_blue_dark"`
*         `app:drawable="@drawable/gradient_drawable"`
*         `app:typeface="@font/font_default"`
*         `app:duration="300"`
*         `android:id="@+id/button_progress"/>`


### Start and stop accordingly

 `var bp: ButtonProgress = findViewById(com.example.buttonprogressbar.R.id.button_progress);`

        `bp.setOnClickListener {`
            
            `//start Animation`
            `bp.setAnim(Anim.RESUME)`

            `Handler().postDelayed(object:Runnable{`

                `override fun run() {`
                    
                    `//stop Animation`
                    `bp.setAnim(Anim.STOP)`
                `}`


            `},2000)`
        `}`

### Modify

        `app:text="Click"`
        `app:textColor="@color/white"`
        `app:textSize="12"`
        `app:backgroundColor="@android:color/holo_green_dark"`
        `app:progressBackground="@android:color/holo_blue_dark"`
        `app:drawable="@drawable/gradient_drawable"`
        `app:typeface="@font/font_default"`
        `app:duration="300"`

        
