Open issues that I encountered and also see:
- there are very rare race conditions with the locks
- there is a rounding issue that sometimes the overall balance is one int to small (99'999 instead of 100'000)
- BankRunner isn't really nice to look at in the context of JUnit
- no unit tests anywhere :D